package fr.eulbobo.bloomdemo.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.eulbobo.bloomdemo.items.HttpKeyCheck;
import fr.eulbobo.bloomdemo.items.ResponseHolder;
import fr.eulbobo.bloomdemo.items.SyncTunnel;
import fr.eulbobo.bloomdemo.sync.Transfert;

@Controller
public class KeyExistsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyExistsController.class);

    @GetMapping("/checkKey")
    @ResponseBody
    public ResponseEntity<String> greeting(@RequestParam(name = "key", required = true) final String key) {
        final ResponseHolder<ResponseEntity<String>> holder = new ResponseHolder<>();

        final HttpKeyCheck keyCheck = new HttpKeyCheck(holder, key);
        final SyncTunnel tunnel = new SyncTunnel(Transfert.getQueue());

        keyCheck.sendKeyTo(tunnel);

        return holder.get();
    }

}

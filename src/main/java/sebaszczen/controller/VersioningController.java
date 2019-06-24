package sebaszczen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/version")
public class VersioningController {

    @GetMapping("/v1/uri")
    public String uriVersioning1() {
        return "uri1";
    }

    @GetMapping("/v2/uri")
    public String uriVersioning2() {
        return "uri2";
    }

    @GetMapping(value = "/param", params = "version=1")
    public String paramVersioning1() {
        return "param1";
    }

    @GetMapping(value = "/param", params = "version=2")
    public String paramVersioning2() {
        return "param2";
    }

    @GetMapping(value = "/header", headers = "version=1")
    public String headerVersioning1() {
        return "header1";
    }

    @GetMapping(value = "/header", headers = "version=2")
    public String headerVersioning2() {
        return "header2";
    }

    @GetMapping(value = "/consume", produces = "konsumuje/version1")
    public String consumes1() {
        return "consumes1";
    }

    @GetMapping(value = "/consume", produces = "konsumuje/version2")
    public String consumes2() {
        return "consumes2";
    }
}

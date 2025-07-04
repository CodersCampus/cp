package com.coderscampus.cp.web;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/github")
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("")
    public String home(ModelMap model) {
        gitHubService.deleteRecordsWithNoStudentAssociated();
        List<GitHub> gitHubs = gitHubService.findAll();
        model.put("gitHubs", gitHubs);
        model.addAttribute("pageTitle", "GitHub Read");
        model.put("isGitHub", true);
        return "github/read";
    }

    @GetMapping("/create")
    public String getCreate(ModelMap model) {
        GitHub gitHub = new GitHub();
        model.put("gitHub", gitHub);
        model.addAttribute("pageTitle", "GitHub Create");
        model.put("isGitHub", true);
        return "github/create";
    }

    @PostMapping("/create")
    public String create(GitHub gitHub, @RequestParam("uid") String uid, ModelMap model) {
        String url = gitHub.getUrl();

        if (!isValidURL(url)) {
            model.put("gitHub", gitHub);
            model.put("error", "Invalid URL");
            return "github/create";
        }
        gitHub = gitHubService.saveByUid(gitHub, uid);
        return "redirect:/github";
    }

    @GetMapping("/update/{id}")
    public String fetch(ModelMap model, @PathVariable Long id, HttpSession httpSession) {
        String uid = (String) httpSession.getAttribute("uid");
        GitHub gitHub = gitHubService.findById(id);

        if (gitHub.getStudent() != null && gitHub.getStudent().getUid().equals(uid)) {
            model.put("gitHub", gitHub);
            model.addAttribute("pageTitle", "GitHub Update");
            model.put("isGitHub", true);
            return "github/update";
        } else {
            return "redirect:/github";
        }
    }

    @PostMapping("/update")
    public String update(GitHub gitHub, HttpSession httpSession, ModelMap model) {
        if (!isValidURL(gitHub.getUrl())) {
            model.put("gitHub", gitHub);
            model.put("error", "Invalid URL");
            return "github/update";
            }
        String uid = (String) httpSession.getAttribute("uid");
        gitHubService.saveByUid(gitHub, uid);
        return "redirect:/github";
    }

    @PostMapping("/delete")
    public String delete(GitHub gitHub) {
        gitHubService.delete(gitHub);
        return "redirect:/github";
    }

    private boolean isValidURL (String urlString) {
        try {
        URL url = new URL(urlString);
        url.toURI();

        String protocol = url.getProtocol();
        if (!protocol.equals("http") && !protocol.equals("https")) return false;

        String host = url.getHost();
        if (host == null || host.isBlank() || !host.contains(".")) return false;

        String[] hostParts = host.split("\\.");
        String tld = hostParts[hostParts.length - 1].toLowerCase();

        return VALID_TLDS.contains(tld);
    } catch (Exception e) {
        return false;
    }
    }

    private static final Set<String> VALID_TLDS = Set.of(
    "com", "org", "net", "edu", "gov", "io", "dev", "co", "us", "uk", "de", "ca"
);
}

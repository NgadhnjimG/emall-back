package com.example.demo.configuration;

import org.springframework.util.AntPathMatcher;

import java.util.Map;

class CaseInsensitivePathMatcher extends AntPathMatcher {

    @Override
    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables) {
        super.doMatch(pattern.toLowerCase(), path.toLowerCase(), fullMatch, uriTemplateVariables);
        return true;
    }
}

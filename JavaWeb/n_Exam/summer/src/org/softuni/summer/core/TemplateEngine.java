package org.softuni.summer.core;

import org.softuni.summer.api.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public final class TemplateEngine {
    private final String templatesFolderPath;

    private Model model;

    public TemplateEngine(String templatesFolderPath) {
        this.templatesFolderPath = templatesFolderPath;
    }

    private String renderContent(String templateContent) {
        String renderedContent = templateContent;
        try {
            for (Map.Entry<String,Object> attributeEntry : this.model.getAttributes().entrySet()) {
                String attributeEntryPlaceholder = "$(" + attributeEntry.getKey() + ")";
                if (attributeEntryPlaceholder.equals("$(loggedInHeader)")) {
                    renderedContent = renderedContent.replace(attributeEntryPlaceholder, this.loadHeader("loggedInHeader"));
                } else if (attributeEntryPlaceholder.equals("$(loggedOutHeader)")) {
                    renderedContent = renderedContent.replace(attributeEntryPlaceholder, this.loadHeader("loggedOutHeader"));
                } else if (attributeEntryPlaceholder.equals("$(footer)")) {
                    renderedContent = renderedContent.replace(attributeEntryPlaceholder, this.loadFooter());
                } else {
                    renderedContent = renderedContent.replace(attributeEntryPlaceholder, attributeEntry.getValue().toString());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return renderedContent;
    }

    public Model getNewModel() {
        return this.model = new Model();
    }

    public boolean isTemplate(String result) {
        return result.startsWith("template:");
    }

    public String loadTemplate(String templateName) throws IOException {
        String templateContent = String.join("", Files.readAllLines(
                Paths.get(this.templatesFolderPath + templateName + ".html")));

        return this.renderContent(templateContent);
    }

    public String loadFooter() throws IOException {
        String footerContent = "";
        if (new File(this.templatesFolderPath + "footer.html").exists()) {
            footerContent = String.join("", Files.readAllLines(
                    Paths.get(this.templatesFolderPath + "footer.html")));
        }


        return footerContent;
    }

    public String loadHeader(String header) throws IOException {
        String headerContent = "";
        if (new File(this.templatesFolderPath + header + ".html").exists()) {
            headerContent = String.join("", Files.readAllLines(
                    Paths.get(this.templatesFolderPath + header + ".html")));
        }

        return headerContent;
    }
}

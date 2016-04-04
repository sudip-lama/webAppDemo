package db.assignment1.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "Language")
@SessionScoped
public class Language implements Serializable {

    private static final long serialVersionUID = 20140401004L;
    private List<SelectItem> languageList;
    private String selectedLanguage = "EN";
    private static ResourceBundle valueOf;
    private final static Map languageMap = new HashMap();
    private final Locale defaultLocale = new Locale("en", "US");

    public Language() {
        languageMap.put("English", "EN");
        languageMap.put("नेपाली", "NP");
        languageMap.put("AR", "AR");
    }

    public void onLanguageChange() {
        valueOf = null;
    }

    public List<SelectItem> getLanguageList() {
        if (languageList == null) {
            languageList = new ArrayList<>();
            SelectItem tempSelectItem;
            for (Object tempObject : languageMap.keySet()) {
                tempSelectItem = new SelectItem(languageMap.get(tempObject).toString(), tempObject.toString());
                languageList.add(tempSelectItem);
            }
        }
        return languageList;
    }

    public void setLanguageList(List<SelectItem> languageList) {
        this.languageList = languageList;
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public ResourceBundle getValueOf() throws Exception {
        if (valueOf == null) {
            valueOf = ResourceBundle.getBundle(selectedLanguage, defaultLocale);
        }
        return valueOf;
    }

    public void setValueOf(ResourceBundle valueOf) {
        Language.valueOf = valueOf;
    }
}

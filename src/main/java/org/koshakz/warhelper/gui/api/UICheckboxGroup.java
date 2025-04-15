package org.koshakz.warhelper.gui.api;

import java.util.ArrayList;
import java.util.List;

public class UICheckboxGroup { //Опасно
    private final List<UICheckbox> checkboxes = new ArrayList<>();

    public void add(UICheckbox checkbox) {
        if (checkbox == null || checkboxes.contains(checkbox)) {
            return;
        }
        checkboxes.add(checkbox);
        checkbox.setGroup(this);
    }

    void onCheckboxSelected(UICheckbox selected) {
        for (UICheckbox checkbox : checkboxes) {
            if (checkbox != selected) {
                checkbox.setChecked(false);
            }
        }
    }
}
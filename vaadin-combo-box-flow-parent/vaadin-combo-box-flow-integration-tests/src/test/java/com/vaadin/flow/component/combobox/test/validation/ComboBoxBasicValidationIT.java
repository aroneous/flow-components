package com.vaadin.flow.component.combobox.test.validation;

import com.vaadin.flow.component.combobox.testbench.ComboBoxElement;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.validation.AbstractValidationIT;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static com.vaadin.flow.component.combobox.test.validation.ComboBoxBasicValidationPage.ENABLE_CUSTOM_VALUE_BUTTON;
import static com.vaadin.flow.component.combobox.test.validation.ComboBoxBasicValidationPage.REQUIRED_BUTTON;

@TestPath("vaadin-combo-box/validation/basic")
public class ComboBoxBasicValidationIT
        extends AbstractValidationIT<ComboBoxElement> {

    @Test
    public void fieldIsInitiallyValid() {
        assertClientValid();
        assertServerValid();
    }

    @Test
    public void triggerBlur_assertValidity() {
        testField.sendKeys(Keys.TAB);
        assertServerValid();
        assertClientValid();
    }

    @Test
    public void required_triggerBlur_assertValidity() {
        $("button").id(REQUIRED_BUTTON).click();

        testField.sendKeys(Keys.TAB);
        assertServerInvalid();
        assertClientInvalid();
    }

    @Test
    public void required_changeValue_assertValidity() {
        $("button").id(REQUIRED_BUTTON).click();

        testField.selectByText("foo");
        assertServerValid();
        assertClientValid();

        testField.clear();
        assertServerInvalid();
        assertClientInvalid();

        // Try enter custom value
        testField.sendKeys("custom", Keys.TAB);
        assertServerInvalid();
        assertClientInvalid();
    }

    @Test
    public void required_customValuesAllowed_assertValidity() {
        $("button").id(REQUIRED_BUTTON).click();
        $("button").id(ENABLE_CUSTOM_VALUE_BUTTON).click();

        testField.sendKeys(Keys.TAB);
        assertServerInvalid();
        assertClientInvalid();

        testField.sendKeys("custom", Keys.TAB);
        assertServerValid();
        assertClientValid();
    }

    @Test
    public void detach_attach_preservesInvalidState() {
        // Make field invalid
        $("button").id(REQUIRED_BUTTON).click();
        testField.sendKeys(Keys.TAB);

        detachAndReattachField();

        assertServerInvalid();
        assertClientInvalid();
    }

    @Test
    public void webComponentCanNotModifyInvalidState() {
        assertWebComponentCanNotModifyInvalidState();

        detachAndReattachField();

        assertWebComponentCanNotModifyInvalidState();
    }

    @Test
    public void clientSideInvalidStateIsNotPropagatedToServer() {
        // Make the field invalid
        $("button").id(REQUIRED_BUTTON).click();
        testField.sendKeys(Keys.TAB);

        executeScript("arguments[0].invalid = false", testField);

        assertServerInvalid();
    }

    @Override
    protected ComboBoxElement getTestField() {
        return $(ComboBoxElement.class).first();
    }
}

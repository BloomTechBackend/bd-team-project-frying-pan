package com.amazon.ata.testGenerator.service.dependency;

import com.amazon.ata.testGenerator.service.activity.accounts.*;
import com.amazon.ata.testGenerator.service.activity.terms.GenerateTestActivity;
import com.amazon.ata.testGenerator.service.activity.testTemplates.CreateTestTemplateActivity;
import com.amazon.ata.testGenerator.service.activity.testTemplates.DeleteTestTemplateActivity;
import com.amazon.ata.testGenerator.service.activity.testTemplates.GetTestTemplateActivity;
import com.amazon.ata.testGenerator.service.activity.testTemplates.UpdateTestTemplateActivity;
import dagger.Component;
import dagger.Module;

import javax.inject.Singleton;

/**
 * Component Interface to create Dagger Components of the Services
 *
 */
@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {

    CreateAccountActivity provideCreateAccountActivity();
    DeleteAccountActivity provideDeleteAccountActivity();
    LoginAccountActivity provideLoginAccountActivity();
    LogOutAccountActivity provideLogOutAccountActivity();
//
//    CreateTestTemplateActivity provideCreateTestTemplateActivity();
//    DeleteTestTemplateActivity provideDeleteTestTemplateActivity();
//    GetTestTemplateActivity provideGetTestTemplateActivity();
//    UpdateTestTemplateActivity provideUpdateTestTemplateActivity();
//
//    GenerateTestActivity provideGenerateTestActivity();
}

package com.amazon.ata.testGenerator.service.dependency;

import com.amazon.ata.testGenerator.service.activity.accounts.*;
import com.amazon.ata.testGenerator.service.activity.terms.CreateCustomTermActivity;
import com.amazon.ata.testGenerator.service.activity.terms.DeleteCustomTermActivity;
import com.amazon.ata.testGenerator.service.activity.terms.GetTermsByTemplateActivity;
import com.amazon.ata.testGenerator.service.activity.terms.GetTermsByUsernameActivity;
import com.amazon.ata.testGenerator.service.activity.testTemplates.*;
import com.amazon.ata.testGenerator.service.activity.tests.GenerateTestActivity;
import com.amazon.ata.testGenerator.service.models.terms.results.CreateCustomTermResult;
import com.amazon.ata.testGenerator.service.models.testTemplates.results.GetTemplateByUsernameTitleResult;
import dagger.Component;

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
    IsLoggedInAccountActivity provideIsLoggedInAccountActivity();

    // Templates
    CreateTestTemplateActivity provideCreateTestTemplateActivity();
    DeleteTestTemplateActivity provideDeleteTestTemplateActivity();
    GetTestTemplateActivity provideGetTestTemplateActivity();
    UpdateTestTemplateActivity provideUpdateTestTemplateActivity();

    // templates GSI
    GetTemplateByUsernameTitleActivity  provideGetTemplateByUsernameTitleActivity();
    GetTemplateByUsernameDateActivity provideGetTemplateByUsernameDateActivity();

    // Terms
    CreateCustomTermActivity provideCreateCustomTermActivity();
    DeleteCustomTermActivity provideDeleteCustomTermActivity();

    // Terms GSI
    GetTermsByTemplateActivity provideGetTermsByTemplateActivity();
    GetTermsByUsernameActivity provideGetTermsByUsernameActivity();

    GenerateTestActivity provideGenerateTestActivity();

}

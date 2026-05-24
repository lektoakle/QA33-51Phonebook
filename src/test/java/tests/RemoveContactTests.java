package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    User currentUser = User.builder()
            .email("testuser@test.com")
            .password("aaA1234#")
            .build();

    @BeforeMethod
    public void precondition() throws InterruptedException {
        if (app.getHelperUser().isLoggedIn()) {
            app.getHelperUser().logOut();
        }
        app.getHelperUser().login(currentUser);
//        wait(500);

         app.getHelperContact().provideContacts();
         //if list of contacts <3 ---> add 3 contacts
    }





    @Test
    public void removeFirstContactTest(){
        app.getHelperContact().openContacts();
        int contactsListSizeBeforeRemoval = app.getHelperContact().getContactsListSize();
        System.out.println(contactsListSizeBeforeRemoval);
        app.getHelperContact().removeFirstContact();
        app.getHelperContact().pause(10000);
        int contactsListSizeAfterRemoval = app.getHelperContact().getContactsListSize();
        System.out.println(contactsListSizeAfterRemoval);
        Assert.assertEquals(contactsListSizeBeforeRemoval-contactsListSizeAfterRemoval, 1);

        //Assert size contact list less by one
    }

    @Test
    public void removeAllContactsTest(){
        app.getHelperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperContact().getContactsListSize(), 0);
        Assert.assertTrue(app.getHelperContact().isNoContactsMessagePresent());
        //Assert -->"No contacts here" is present
    }
}

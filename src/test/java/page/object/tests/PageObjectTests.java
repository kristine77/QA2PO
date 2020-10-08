package page.object.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.object.BaseFunc;
import page.object.pages.ArticlePage;
import page.object.pages.CommentsPage;
import page.object.pages.HomePage;

public class PageObjectTests {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Test
    public void delfiPageObjectTest() {
        LOGGER.info("This test will check article title and comment count on Home, Article and Comment pages");

        BaseFunc baseFunc = new BaseFunc();
        baseFunc.openPage("delfi.lv");

        //constructor
        HomePage homePage = new HomePage(baseFunc);

        Integer commentCount = homePage.getCommentCountById(0);


        String homePageTitle = homePage.getTitleById(2);
        ArticlePage articlePage = homePage.openArticle(2);

        Integer articlePageComments = articlePage.getCommentCount();
        Assertions.assertEquals(commentCount, articlePageComments, "Wrong comment count (Article Page)");
        LOGGER.info("Checking home page and article page comment count");

        Assertions.assertEquals(homePageTitle, articlePage.getTitle(), "Wrong title on Article Page");
        LOGGER.info("Checking article title on home page and article page");

        CommentsPage commentsPage = articlePage.openComments();
        Assertions.assertEquals(homePageTitle, commentsPage.getTitle(), "Wrong title on Comments Page");
        LOGGER.info("Checking home page and comments page article title");

        Integer commentsPageComments = commentsPage.getCommentsPageComments();
        Assertions.assertEquals(commentCount, commentsPageComments, "Wrong comment count (Comments Page)");
        LOGGER.info("Checking home page and comments page comment count");

    }
}

package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.BaseClass;
import utils.Config;
import utils.TestNgListener;

import java.util.Set;

@Listeners(TestNgListener.class)
public class MMT_HotelsBooking extends BaseClass {

    WebDriver driver;
    TestNgListener testNgListener = new TestNgListener();
    String firstName = Config.getFirstName();
    String lastName = Config.getLastName();
    String email = Config.getEmail();
    String phone = Config.getMobileNo();
    String country = Config.getCountry();
    String city = Config.getCity();
    String rating = Config.getRating();
    String leasire = Config.getRating();
    String largeBed = Config.getLargeBed();
    String twinBed = Config.getTwinBed();
    int minPriceRange = Config.getMinPriceRange();
    int adult_count = 2;
    int child_count = 2;


    @BeforeClass
    public void setup() {
        initDriver();
        driver = getDriver();
        initPages(driver);
    }

    @BeforeMethod
    public void init() {
        driver.get(Config.getUrl());
    }

    @Test
    public void mmt_BookHotelGuest() {
        String windowHandle = driver.getWindowHandle();
        //hotel filter
        hotelsPage.selectHotelMenu();
        hotelsPage.selectCity(city);
        hotelsPage.selectRndVacationDates();
        hotelsPage.selectAdultAndChildCount(adult_count, child_count);

        hotelsPage.addRoom();
        hotelsPage.selectAdultAndChildCount(adult_count, child_count);
        hotelsPage.clickApplyButton();
        hotelsPage.getTravelingFor(leasire);

        String actualCheckInDate = hotelsPage.getCheckInDate();
        String actualCheckOutDate = hotelsPage.getCheckOutDate();
        hotelsPage.searchHotels();

        hotelSearchResultsPage.dismissLocationPopUp();
        hotelSearchResultsPage.selectMinPriceRangeFromFilter(minPriceRange);

        //hotels search
        hotelSearchResultsPage.selectUserRating(rating);
        String actualHotelName = hotelSearchResultsPage.selectHotel(5);
        hotelSearchResultsPage.switchToNewTab(windowHandle);

        String actualLocation = hotelDetailsPage.getHotelLocation();

        //hotel details page
        hotelDetailsPage.clickRoomsTab();
        Set<String> rooms = hotelDetailsPage.selectRoom(1);

        reviewBookingPage.closeModalIfExists();
        reviewBookingPage.enterName(firstName, lastName);
        reviewBookingPage.enterEmailID(email);
        reviewBookingPage.enterIndianMobile(phone);

        reviewBookingPage.selectCommonRequests(largeBed);
        reviewBookingPage.selectCommonRequests(twinBed);

        reviewBookingPage.setDonationCheckbox(false);
        String actualPrice = reviewBookingPage.getTotalPrice();

        reviewBookingPage.clickPayNowButton();

        //actual assertions
        String expectedHotelName = bookingSummaryPage.getHotelName();
        String expectedLocation = bookingSummaryPage.getHotelLocation();
        String expectedCheckInDate = bookingSummaryPage.getCheckInTime();
        String expectedCheckOutDate = bookingSummaryPage.getCheckOutTime();
        String expectedFullName = bookingSummaryPage.getTravellerName();
        String expectedContactInfo = bookingSummaryPage.getContactInfo();
        String expectedRoomType = bookingSummaryPage.getRoomType();
        String expectedFinalPrice = bookingSummaryPage.getFinalPayableAmount();

        testNgListener.assertFailAndContinue(driver, expectedHotelName.equalsIgnoreCase(actualHotelName), "Expected hotel name not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedLocation.contains(actualLocation), "Expected hotel location not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedCheckInDate.contains(actualCheckInDate), "Expected hotel location not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedCheckOutDate.contains(actualCheckOutDate), "Expected hotel location not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedFullName.equalsIgnoreCase(firstName + " " + lastName), "Expected full name not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedContactInfo.equalsIgnoreCase(phone + ", " + email), "Expected contact info not matched with actual");
        //testNgListener.assertFailAndContinue(driver, rooms.contains(expectedRoomType), "Expected room type not matched with actual");
        testNgListener.assertFailAndContinue(driver, actualPrice.contains(expectedFinalPrice), "Expected price not matched with actual");

    }

    @Test
    public void mmt_BookHotelRegistered() {
        String windowHandle = driver.getWindowHandle();
        homePage.selectCountry(country);
        homePage.getLoginFrame();
        homePage.login(Config.getUserName(), Config.getPassword());
        homePage.checkLoggedInUser();

        //hotel filter
        hotelsPage.selectHotelMenu();
        hotelsPage.selectCity(city);
        hotelsPage.selectRndVacationDates();
        hotelsPage.selectAdultAndChildCount(adult_count, child_count);

        hotelsPage.addRoom();
        hotelsPage.selectAdultAndChildCount(adult_count, child_count);
        hotelsPage.clickApplyButton();
        hotelsPage.getTravelingFor(leasire);

        String actualCheckInDate = hotelsPage.getCheckInDate();
        String actualCheckOutDate = hotelsPage.getCheckOutDate();
        hotelsPage.searchHotels();

        hotelSearchResultsPage.dismissLocationPopUp();
        hotelSearchResultsPage.selectMinPriceRangeFromFilter(minPriceRange);

        //hotels search
        hotelSearchResultsPage.selectUserRating(rating);
        String actualHotelName = hotelSearchResultsPage.selectHotel(5);
        hotelSearchResultsPage.switchToNewTab(windowHandle);

        String actualLocation = hotelDetailsPage.getHotelLocation();

        //hotel details page
        hotelDetailsPage.clickRoomsTab();
        Set<String> rooms = hotelDetailsPage.selectRoom(1);

        reviewBookingPage.closeModalIfExists();
        reviewBookingPage.enterName(firstName, lastName);
        reviewBookingPage.enterEmailID(email);
        reviewBookingPage.enterIndianMobile(phone);

        reviewBookingPage.selectCommonRequests(largeBed);
        reviewBookingPage.selectCommonRequests(twinBed);

        reviewBookingPage.setDonationCheckbox(false);
        String actualPrice = reviewBookingPage.getTotalPrice();

        reviewBookingPage.clickPayNowButton();

        //actual assertions
        String expectedHotelName = bookingSummaryPage.getHotelName();
        String expectedLocation = bookingSummaryPage.getHotelLocation();
        String expectedCheckInDate = bookingSummaryPage.getCheckInTime();
        String expectedCheckOutDate = bookingSummaryPage.getCheckOutTime();
        String expectedFullName = bookingSummaryPage.getTravellerName();
        String expectedContactInfo = bookingSummaryPage.getContactInfo();
        String expectedRoomType = bookingSummaryPage.getRoomType();
        String expectedFinalPrice = bookingSummaryPage.getFinalPayableAmount();

        testNgListener.assertFailAndContinue(driver, expectedHotelName.equalsIgnoreCase(actualHotelName), "Expected hotel name not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedLocation.contains(actualLocation), "Expected hotel location not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedCheckInDate.contains(actualCheckInDate), "Expected hotel location not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedCheckOutDate.contains(actualCheckOutDate), "Expected hotel location not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedFullName.equalsIgnoreCase(firstName + " " + lastName), "Expected full name not matched with actual");
        testNgListener.assertFailAndContinue(driver, expectedContactInfo.equalsIgnoreCase(phone + ", " + email), "Expected contact info not matched with actual");
        testNgListener.assertFailAndContinue(driver, rooms.contains(expectedRoomType), "Expected room type not matched with actual");
        testNgListener.assertFailAndContinue(driver, actualPrice.contains(expectedFinalPrice), "Expected price not matched with actual");
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}

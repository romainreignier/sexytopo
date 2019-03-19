package org.hwyl.sexytopo.control.io.basic;

import org.hwyl.sexytopo.demo.TestSurveyCreator;
import org.hwyl.sexytopo.model.survey.Survey;
import org.hwyl.sexytopo.testhelpers.BasicTestSurveyCreator;
import org.hwyl.sexytopo.testhelpers.SurveyChecker;
import org.junit.Test;


public class SurveyJsonTranslaterTest {

    public final static String TEST_SURVEY_NAME = "TestSurvey";

    @Test
    public void testEmptySurveyResultsIn1Station() throws Exception {
        Survey survey = new Survey(TEST_SURVEY_NAME);
        String text = SurveyJsonTranslater.toText(survey, "test", 0);

        Survey newSurvey = new Survey(TEST_SURVEY_NAME);
        SurveyJsonTranslater.populateSurvey(survey, text);
        assert newSurvey.getAllStations().size() == 1;
    }

    @Test
    public void testSimpleSurveyIsParsed() throws Exception {
        Survey survey = BasicTestSurveyCreator.createStraightNorth();
        String text = SurveyJsonTranslater.toText(survey, "test", 0);

        Survey newSurvey = new Survey(TEST_SURVEY_NAME);
        SurveyJsonTranslater.populateSurvey(survey, text);

        SurveyChecker.areEqual(survey, newSurvey);
    }


    @Test
    public void testSlightlyBiggerSurveyIsParsed() throws Exception {
        Survey survey = BasicTestSurveyCreator.createRightRight();
        String text = SurveyJsonTranslater.toText(survey, "test", 0);

        Survey newSurvey = new Survey(TEST_SURVEY_NAME);
        SurveyJsonTranslater.populateSurvey(survey, text);

        SurveyChecker.areEqual(survey, newSurvey);
    }

    @Test
    public void testRandomSurveyIsParsed() throws Exception {
        Survey survey = TestSurveyCreator.create(TEST_SURVEY_NAME, 10, 10);
        String text = SurveyJsonTranslater.toText(survey, "test", 0);

        Survey newSurvey = new Survey(TEST_SURVEY_NAME);
        SurveyJsonTranslater.populateSurvey(survey, text);

        SurveyChecker.areEqual(survey, newSurvey);
    }

    @Test
    public void testSurveyWithTripsAreParsed() throws Exception {
        Survey survey = BasicTestSurveyCreator.createStraightNorthWithTrips();
        String text = SurveyJsonTranslater.toText(survey, "test", 0);

        Survey newSurvey = new Survey(TEST_SURVEY_NAME);
        SurveyJsonTranslater.populateSurvey(survey, text);

        SurveyChecker.areEqual(survey, newSurvey);
    }
}

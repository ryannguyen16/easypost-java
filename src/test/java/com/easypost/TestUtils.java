package com.easypost;

import com.easypost.easyvcr.AdvancedSettings;
import com.easypost.easyvcr.Cassette;
import com.easypost.easyvcr.Censors;
import com.easypost.easyvcr.MatchRules;

import java.io.File;
import java.nio.file.Paths;

public class TestUtils {
    private static final String ApiKeyFailedToPull = "couldnotpullapikey";

    private static final String CassettesFolder = "cassettes";

    private static String getSourceFileDirectory() {
        try {
            return Paths.get("").toAbsolutePath().toString();
        }
        catch (Exception e) {
            return "";
        }
    }

    private static String getApiKey(ApiKey apiKey) {
        String keyName = "";
        switch (apiKey)
        {
            case TEST:
                keyName = "EASYPOST_TEST_API_KEY";
                break;
            case PRODUCTION:
                keyName = "EASYPOST_PROD_API_KEY";
                break;
            default:
                break;
        }

        String value = System.getenv(keyName);
        return value != null ? value : ApiKeyFailedToPull; // if can't pull from environment, will use a fake key. Won't matter on replay.
    }

    public enum ApiKey {
        TEST,
        PRODUCTION
    }

    public static class VCR {
        private final com.easypost.easyvcr.VCR _vcr;

        private final String _apiKey;

        private String _testCassettesFolder;

        public VCR(String testCassettesFolder, ApiKey apiKey) {
            AdvancedSettings advancedSettings = new AdvancedSettings();
            advancedSettings.matchRules = MatchRules.strict(); // match by method, url and body
            advancedSettings.censors = Censors.strict();
            advancedSettings.simulateDelay = false;
            advancedSettings.manualDelay = 0;

            _vcr = new com.easypost.easyvcr.VCR(advancedSettings);
            _vcr.recordIfNeeded(); // always in auto mode

            _apiKey = getApiKey(apiKey);

            _testCassettesFolder = Paths.get(getSourceFileDirectory(), CassettesFolder).toString(); // create "cassettes" folder in same directory as test files

            if (testCassettesFolder != null)
            {
                _testCassettesFolder = Paths.get(_testCassettesFolder, testCassettesFolder).toString(); // create test group folder in .NET version-specific folder
            }

            // if folder doesn't exist, create it
            File directory = new File(_testCassettesFolder);
            if (! directory.exists()){
                directory.mkdirs();
            }
        }

        public VCR(String testCassettesFolder) {
            this(testCassettesFolder, ApiKey.TEST);
        }

        public VCR(ApiKey apiKey) {
            this(null, apiKey);
        }

        public VCR() {
            this(null, ApiKey.TEST);
        }

        public void setUpTest(String cassetteName, String overrideApiKey)
        {
            // override api key if needed
            EasyPost.apiKey = overrideApiKey != null ? overrideApiKey : _apiKey;

            // set up cassette
            Cassette cassette = new Cassette(_testCassettesFolder, cassetteName);

            // add cassette to vcr
            _vcr.insert(cassette);

            // set VCR to be used during requests
            EasyPost.vcr = _vcr;
        }

        public void setUpTest(String cassetteName) {
            setUpTest(cassetteName, null);
        }
    }
}

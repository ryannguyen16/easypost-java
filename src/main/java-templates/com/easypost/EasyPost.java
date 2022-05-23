package com.easypost;

public abstract class EasyPost {
    public static final String VERSION = "${project.version}";
    public static String API_BASE = "https://api.easypost.com/v2";
    public static String BETA_API_BASE = "https://api.easypost.com/beta";
    public static String apiKey;
    public static String apiVersion;
    public static int readTimeout;
    public static VCR vcr = null;
    public static Cassette cassette = null;
}

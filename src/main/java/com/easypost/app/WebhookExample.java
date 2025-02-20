// java -cp "target/easypost-java-2.0.4.jar:target/gson-2.2.2.jar" Webhook
package com.easypost.app;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Event;
import com.easypost.net.EasyPostResource;

public class WebhookExample {

    public static void main(final String[] args) {
        EasyPost.apiKey = System.getenv("EASYPOST_API_KEY");

        try {
            Event event = Event.retrieve("evt_123");
            EasyPostResource tracker = event.getResult();

            System.out.println(event.getDescription());
            System.out.println(tracker.getStatus());
            System.out.println(tracker.prettyPrint());

        } catch (EasyPostException e) {
            e.printStackTrace();
        }
    }
}

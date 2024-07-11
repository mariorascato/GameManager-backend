package it.unimol.mobile.gamemanager.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RefreshScheduler {
    private static final Logger logger = LoggerFactory.getLogger(RefreshScheduler.class);

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 */14 * * * *")
    public void performRefresh() {
        logger.info("Refresh eseguito ogni 14 minuti");

        String url = "https://gamemanager-backend.onrender.com/api/game-manager/refresh/do";

        try {
            String response = restTemplate.getForObject(url, String.class);
            logger.info("Response from HTTP request: {}", response);
        } catch (Exception e) {
            logger.error("Error during HTTP request", e);
        }
    }
}
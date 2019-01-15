package br.com.fiap.twitterapi;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Connection {
	public static void main(String[] args) {

		try {
			TwitterFactory factory = new TwitterFactory();
			AccessToken accessToken = loadAccessToken();
			Twitter twitter = factory.getSingleton();
			twitter.setOAuthConsumer("XFUGfCCVTiNSWBw9hBlX4r0pA", "Z1ZCdPNEWZlTBEQTznHxXBny2KF4m0T1OxCx7foa65qz2dA0Px");
			twitter.setOAuthAccessToken(accessToken);
		/*	List<Status> statuses = twitter.getHomeTimeline();
			System.out.println("Home Timeline:");
			for (Status status : statuses) {
				System.out.println("User: " + status.getUser().getName() + ":Tweet:" + status.getText());
			}
*/
			Query q = new Query("#java");
			q.setResultType(ResultType.mixed);
			
			String sinceLastWeek = getLastWeek();
			q.setSince(getLastWeek() );
			

			
			Map<String,Integer> mapTweetsDia = new HashMap<>();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			Integer countTweetsDay = null;
			int countTweets = 0;
			int countRetweets = 0;
			QueryResult result = twitter.search(q);
			while (result.hasNext()) {
				q = result.nextQuery();
				for (Status status : result.getTweets()) {
					
					String DateToStr = format.format(status.getCreatedAt());
					countTweetsDay = mapTweetsDia.get(DateToStr);
					if( countTweetsDay != null) {
						countTweetsDay += 1;
					} else {
						countTweetsDay = 1;
					}
					
					mapTweetsDia.put(DateToStr, countTweetsDay);
					
					countTweets++;
					countRetweets += status.getRetweetCount();

					

					//System.out.println(DateToStr);

					
					/*System.out.println( status.getCreatedAt() + " @" + status.getUser().getScreenName() + ":"
							+ status.getText());*/
				}
				result = twitter.search(q);
			}
			
			System.out.println("Quantidade de tweets da ultima semana: " + countTweets);
			System.out.println("Quantidade de retweets da ultima semana: " + countRetweets);
			for(Map.Entry<String, Integer> s : mapTweetsDia.entrySet() ) {
				 System.out.println("Dia: " + s.getKey() + " tweets: " + s.getValue() ); 
			}

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getLastWeek() {
		final ZonedDateTime input = ZonedDateTime.now();
		final ZonedDateTime startOfLastWeek = input.minusWeeks(1).with(DayOfWeek.MONDAY);
		String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(startOfLastWeek);
		return date;
	}

	private static AccessToken loadAccessToken() {
		String token = "873142728745521154-yjTXQTIROwnmH9Vcts4vjObbeAivp2o";
		String tokenSecret = "2rufmp9JZbDRBDRt5tvBLEO0gOOXqi0kBvWiMPkcSL1QM";
		return new AccessToken(token, tokenSecret);
	}
}
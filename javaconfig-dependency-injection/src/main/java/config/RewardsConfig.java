package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;

@Configuration
public class RewardsConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public JdbcAccountRepository accountRepository() {
		JdbcAccountRepository result = new JdbcAccountRepository();
		result.setDataSource(dataSource);
		return result;
	}
	
	@Bean
	public JdbcRestaurantRepository restaurantRepository() {
		JdbcRestaurantRepository result = new JdbcRestaurantRepository();
		result.setDataSource(dataSource);
		return result;
	}
	
	@Bean
	public JdbcRewardRepository rewardRespository() {
		JdbcRewardRepository result = new JdbcRewardRepository();
		result.setDataSource(dataSource);
		return result;
	}

	@Bean
	public RewardNetworkImpl rewardNetwork() {
		RewardNetworkImpl result = new RewardNetworkImpl(accountRepository(), restaurantRepository(), rewardRespository());
		return result;
	}
}

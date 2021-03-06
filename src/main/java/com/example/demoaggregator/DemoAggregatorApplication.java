package com.example.demoaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Implements solution's Spring Boot Web ready RESTful interface
 */
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class DemoAggregatorApplication extends WebSecurityConfigurerAdapter {

	private IAggregator m_aggregator;

	public static void main(String[] args) {
		SpringApplication.run(DemoAggregatorApplication.class, args);
	}

	@PostConstruct
	public void init() {
		m_aggregator = new MinMaxAggregator();
	}

	/**
	 * Endpoint to add (submit) number list to the aggregator
	 * @param val
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addNumbers", method = RequestMethod.POST, produces = "text/html",
					consumes = "application/json")
	@ResponseBody
	public ResponseEntity<String> addNumbers(@RequestBody Map<String, Object> val,
													HttpServletRequest request,
													HttpServletResponse response) {
		String numberString = "" + val.getOrDefault("numbers", ""); // use default toString conversion
		// Input Validator filters out all illegal input, producing only list of valid numbers
		InputValidator.validateInput(numberString).stream().forEach(e -> m_aggregator.addNumber(e));
		String reply = m_aggregator.getOutput(new HTMLFormatter());
		return new ResponseEntity<>(reply,  HttpStatus.OK);
	}

	/**
	 * Endpoint to read the aggregator's content
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getNumbers", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public ResponseEntity<String> addNumbers(
			HttpServletRequest request,
			HttpServletResponse response) {
		String reply = m_aggregator.getOutput(new HTMLFormatter());
		return new ResponseEntity<>(reply,  HttpStatus.OK);
	}

	/**
	 * Disable the default Sprin Boot endpoint security, to make things simple
	 * @param http
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable()
				.anonymous().authorities("ROLE_ANONYMOUS")
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/**").permitAll()
				.antMatchers(HttpMethod.GET, "/**").permitAll()		;
		// @formatter:on
	}
}

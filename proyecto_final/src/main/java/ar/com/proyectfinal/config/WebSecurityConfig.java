package ar.com.proyectfinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*aca la decimos que todo lo que ingrese por esa ruta, esta permitido
		sin pedir permiso

		http
				.httpBasic(withDefaults())
				.authorizeRequests()
				.antMatchers("/**").permitAll();

*/
		//DESCOMENTAR PARA TENER EL LOGIN ANDANDO
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/").authenticated()
				.antMatchers("/global/**","/static/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()

				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
				.httpBasic(); // API Rest

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}




	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}

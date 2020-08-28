package com.wallet.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) /* permite depois colocar anotacoes pra autorização */
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static final String[] PUBLIC_MATCHERS_POST = {"**"};


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();/*
											 * pega as configuracoes definidas abaixo no corsConfigurationSource e
											 * desabilita a proteção CSRF
											 */
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		.anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); /* informando que não cria sessao de usuário */
	}
}

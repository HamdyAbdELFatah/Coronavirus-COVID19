package com.coders.coronavirus.countries;

public class DataCountries {


    public DataCountries(String country_name, String cases, String deaths, String total_recovered, String new_deaths,
                         String new_cases, String serious_critical, String active_cases,
                         String total_cases_per_1m_population, String flag) {
        this.country_name = country_name;
        this.cases = cases;
        this.deaths = deaths;
        this.total_recovered = total_recovered;
        this.new_deaths = new_deaths;
        this.new_cases = new_cases;
        this.serious_critical = serious_critical;
        this.active_cases = active_cases;
        this.total_cases_per_1m_population = total_cases_per_1m_population;
        this.flag = flag;
    }

    String country_name,cases,deaths,total_recovered,new_deaths,new_cases,serious_critical,active_cases,
            total_cases_per_1m_population,flag;
}

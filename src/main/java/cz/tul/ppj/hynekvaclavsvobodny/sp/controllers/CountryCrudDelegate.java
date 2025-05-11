package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CountryRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.CountryService;
import org.springframework.stereotype.Component;

@Component
public class CountryCrudDelegate extends NumberIdCrudDelegate<CountryService, CountryRepository, Country> {}

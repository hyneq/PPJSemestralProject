package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.CityService;
import org.springframework.stereotype.Component;

@Component
public class CityCrudDelegate extends NumberIdCrudDelegate<CityService, CityRepository, City> {}

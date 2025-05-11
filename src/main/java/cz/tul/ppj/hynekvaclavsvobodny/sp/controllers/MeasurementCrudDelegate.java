package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.MeasurementService;
import org.springframework.stereotype.Component;

@Component
public class MeasurementCrudDelegate extends CrudDelegate<MeasurementService, MeasurementRepository, Measurement, Measurement.MeasurementId> {
}

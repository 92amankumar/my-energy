package com.myenergy;

import com.myenergy.entity.ElectricityReadings;
import com.myenergy.entity.PowerSupplier;
import com.myenergy.entity.Readings;
import com.myenergy.repository.PowerSupplierRepository;
import com.myenergy.repository.ReadingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SpringBootApplication
public class MyEnergyApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyEnergyApplication.class);
	private static final String DR_EVILS_DARK_ENERGY_ENERGY_SUPPLIER_ID = "price-plan-0";
	private static final String THE_GREEN_ECO_ENERGY_SUPPLIER_ID = "price-plan-1";
	private static final String POWER_FOR_EVERYONE_ENERGY_SUPPLIER_ID = "price-plan-2";

//	public static final List<String> SUPPLIER_IDS = Arrays.asList(new String[]{DR_EVILS_DARK_ENERGY_ENERGY_SUPPLIER_ID, THE_GREEN_ECO_ENERGY_SUPPLIER_ID, POWER_FOR_EVERYONE_ENERGY_SUPPLIER_ID});

	private static final String SARAH_METER_ID = "smart-meter-0";
	private static final String PETER_METER_ID = "smart-meter-1";
	private static final String CHARLIE_METER_ID = "smart-meter-2";
	private static final String ANDREA_METER_ID = "smart-meter-3";
	private static final String ALEX_METER_ID = "smart-meter-4";

//	public static final List<String> METER_IDS = Arrays.asList(new String[]{SARAH_METER_ID, PETER_METER_ID, CHARLIE_METER_ID,ANDREA_METER_ID,ALEX_METER_ID});

	private static final String DR_EVILS_DARK_ENERGY_ENERGY_SUPPLIER = "Dr Evil's Dark Energy";
	private static final String THE_GREEN_ECO_ENERGY_SUPPLIER = "The Green Eco";
	private static final String POWER_FOR_EVERYONE_ENERGY_SUPPLIER = "Power for Everyone";

	private static final Random random = new Random();
	@Autowired
	private ReadingsRepository readingsRepository;

	@Autowired
	private PowerSupplierRepository powerSupplierRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyEnergyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PowerSupplier powerSupplier1 = new PowerSupplier(DR_EVILS_DARK_ENERGY_ENERGY_SUPPLIER_ID, DR_EVILS_DARK_ENERGY_ENERGY_SUPPLIER, BigDecimal.valueOf(100));
		PowerSupplier powerSupplier2 = new PowerSupplier(THE_GREEN_ECO_ENERGY_SUPPLIER_ID, THE_GREEN_ECO_ENERGY_SUPPLIER, BigDecimal.valueOf(50));
		PowerSupplier powerSupplier3 = new PowerSupplier(POWER_FOR_EVERYONE_ENERGY_SUPPLIER_ID, POWER_FOR_EVERYONE_ENERGY_SUPPLIER, BigDecimal.valueOf(10));
		powerSupplierRepository.save(powerSupplier1);
		powerSupplierRepository.save(powerSupplier2);
		powerSupplierRepository.save(powerSupplier3);
		
		readingsRepository.save(generateReadings(SARAH_METER_ID, DR_EVILS_DARK_ENERGY_ENERGY_SUPPLIER_ID));
		readingsRepository.save(generateReadings(PETER_METER_ID, THE_GREEN_ECO_ENERGY_SUPPLIER_ID));
		readingsRepository.save(generateReadings(CHARLIE_METER_ID, DR_EVILS_DARK_ENERGY_ENERGY_SUPPLIER_ID));
		readingsRepository.save(generateReadings(ANDREA_METER_ID, POWER_FOR_EVERYONE_ENERGY_SUPPLIER_ID));
		readingsRepository.save(generateReadings(ALEX_METER_ID, THE_GREEN_ECO_ENERGY_SUPPLIER_ID));

		LOGGER.info("Init Success!!!");
	}

	private Readings generateReadings(String smartMeterId, String powerSupplierId){
		Readings readings = new Readings();
		readings.setSmartMeterId(smartMeterId);
		Set<ElectricityReadings> electricityReadingsSet = new HashSet<>();
		for (int i=0; i < 5; i++){
			electricityReadingsSet.add(new ElectricityReadings(readings, Date.from(Instant.now().minusSeconds(i * 20l)), BigDecimal.valueOf(random.nextDouble())));
		}
		readings.setElectricityReadings(electricityReadingsSet);
		PowerSupplier powerSupplier = powerSupplierRepository.findById(powerSupplierId).orElseThrow(() -> new RuntimeException("Not Found"));
		readings.setPricePlanId(powerSupplier);
		return readings;
	}
}

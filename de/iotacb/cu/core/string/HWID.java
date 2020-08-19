package de.iotacb.cu.core.string;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.collect.ComputationException;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Memory;
import oshi.software.os.OperatingSystem;
import oshi.hardware.Processor;
import oshi.software.os.linux.proc.CentralProcessor;

public class HWID {

	/**
	 * Will return MD5 hashed info about the computer
	 * @return
	 */
	public static final String generateHWID() {
		final SystemInfo systemInfo = new SystemInfo();
		final OperatingSystem os = systemInfo.getOperatingSystem();
		final HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		final Processor processor = hardwareAbstractionLayer.getProcessors()[0];
		
		final String vendor = DigestUtils.md5Hex(os.getManufacturer());
		final String identifier = DigestUtils.md5Hex(processor.getIdentifier());
		final String username = DigestUtils.md5Hex(System.getProperty("user.name"));
		
		return vendor.concat("-").concat(identifier).concat("-").concat(username);
	}
	
}

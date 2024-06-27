package net.clesperanto.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.clesperanto._internals.jclic.StringVector;

/**
 * Class to interact with the divide that is going to be used to do the operations
 */
public class DeviceJ {
	
	protected net.clesperanto._internals.jclic.DeviceJ jcppDeviceJ;
	
	/**
	 * Constructor that initializes the default device
	 */
	public DeviceJ() {
		jcppDeviceJ = new net.clesperanto._internals.jclic.DeviceJ();
	}
	
	/**
	 * Constructor that initializes the wanted device
	 * 
	 * TODO provide a better explanation of what deviceName is and what device type is
	 * 
	 * @param deviceName
	 * 	the name of the device that wants to be initialized
	 * @param deviceType
	 * 	the type that wants to be initialized. If any type works, the argument should be "all"
	 */
	public DeviceJ(String deviceName, String deviceType) {
		Objects.requireNonNull(deviceName, "The device name cannot be null");
		Objects.requireNonNull(deviceType, "The device type cannot be null, if any device type works, use \"all\"");
		jcppDeviceJ = new net.clesperanto._internals.jclic.DeviceJ();
		jcppDeviceJ.setDevice(deviceName, deviceType);
	}
	
	/**
	 * 
	 * @return the name of the current device
	 */
	public String getName() {
		return this.jcppDeviceJ.getName();
	}
	
	/**
	 * 
	 * @return the info about the current device
	 */
	public String getInfo() {
		return this.jcppDeviceJ.getInfo();
	}
	
	/**
	 * Change the current device to the wanted one
	 * @param deviceName
	 * 	the name of the device that wants to be used
	 * @param deviceType
	 * 	the type that wants to be used. If any type works, the argument should be "all"
	 */
	public void setDevice(String deviceName, String deviceType) {
		jcppDeviceJ.setDevice(deviceName, deviceType);
	}
	
	/**
	 * TODO confirm if the devices are only GPUs or can be other hardware
	 * Method that returns the available devices (GPUs) on the computer.
	 * It is the same as using {@link #getAvailableDevices(String)} with deviceType 'all'
	 * @param deviceType
	 * 	the type of devicethe method has to look for
	 * @return a list of the available devices in the computer of the specific type
	 */
	public static List<String> getAvailableDevices(){
		StringVector devices = net.clesperanto._internals.jclic.DeviceJ.getAvailableDevices();
		List<String> devicesList = new ArrayList<String>();
		for (int i = 0; i < devices.size(); i++) {
			devicesList.add(devices.get(i));
        }
		return devicesList;
	}
	
	/**
	 * TODO confirm if the devices are only GPUs or can be other hardware
	 * Method that returns the available devices (GPUs) on the computer by the type..
	 * Using the type "all" returns all the devices available, it is the same as using {@link #getAvailableDevices()}
	 * @param deviceType
	 * 	the type of devicethe method has to look for
	 * @return a list of the available devices in the computer of the specific type
	 */
	public static List<String> getAvailableDevices(String deviceType){
		Objects.requireNonNull(deviceType, "The device type cannot be null, if any device type works, use \"all\" or"
				+ " use the method \"DeviceJ.getAvailableDevices()\"");
		StringVector devices = net.clesperanto._internals.jclic.DeviceJ.getAvailableDevices(deviceType);
		List<String> devicesList = new ArrayList<String>();
		for (int i = 0; i < devices.size(); i++) {
			devicesList.add(devices.get(i));
        }
		return devicesList;
	}
    
    /**
     * 
     * @return the raw object that is going to be sent to the native Clesperanto library. Without Java wrappers
     */
    public net.clesperanto._internals.jclic.DeviceJ getRaw() {
    	return this.jcppDeviceJ;
    }
}
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
	 * IMPORTANT: Does not initialize the backend.
	 *
	 *
	 */
	protected DeviceJ() {
		jcppDeviceJ = new net.clesperanto._internals.jclic.DeviceJ();
	}

	/**
	 * Constructor that initializes the wanted device
	 * IMPORTANT: Does not initialize the backend.
	 *
	 * TODO provide a better explanation of what deviceName is and what device type is
	 *
	 * @param deviceName
	 * 	the name of the device that wants to be initialized
	 * @param deviceType
	 * 	the type that wants to be initialized. If any type works, the argument should be "all"
	 */
	protected DeviceJ(String deviceName, String deviceType) {
		Objects.requireNonNull(deviceName, "The device name cannot be null");
		Objects.requireNonNull(deviceType, "The device type cannot be null, if any device type works, use \"all\"");
		jcppDeviceJ = new net.clesperanto._internals.jclic.DeviceJ();
		jcppDeviceJ.setDevice(deviceName, deviceType);
	}

	/**
	 * Get the first device available. By default this method tries to use an OpenCL backend.
	 * If not OpenCL backend is available the method will fail.
	 * @return the default device where Clesperanto operations can be done.
	 */
	public static DeviceJ getDefaultDevice() {
		BackendJ.setBackend("");
		return new DeviceJ();
	}

	/**
	 * Get the first device available and select the wanted backend.
	 * If the wanted backend is not available, the method will fall back to OpenCL.
	 * And if OpenCL is not available either the method will fail.
	 * @param backend
	 * 	the type of backend that wants to be used. It should be either "cuda" or "opencl",
	 * 	if it is anything else it will be set to "opencl"
	 * @return the default device where Clesperanto operations can be done.
	 */
	public static DeviceJ getDefaultDevice(String backend) {
		BackendJ.setBackend(backend);
		return new DeviceJ();
	}

	/**
	 * Get the wanted device by its name and device type. Initialize the device with openCL backend.
	 * If not OpenCL backend is available the method will fail.
	 *
	 * TODO provide a better explanation of what deviceName is and what device type is
	 *
	 * @param deviceName
	 * 	the name of the device that wants to be initialized
	 * @param deviceType
	 * 	the type that wants to be initialized. If any type works, the argument should be "all"
	 * @return the wanted device where Clesperanto operations can be done.
	 */
	public static DeviceJ getDeviceWithDefaultBackend(String deviceName, String deviceType) {
		BackendJ.setBackend("");
		return new DeviceJ(deviceName, deviceType);
	}

	/**
	 * Get the wanted device by its name and device type. Initialize the device with
	 * the wanted backend. If the backend is not available it will fallback to OpenCl backend.
	 * If OpenCL backend is not available the method will fail.
	 *
	 * TODO provide a better explanation of what deviceName is and what device type is
	 *
	 * @param deviceName
	 * 	the name of the device that wants to be initialized
	 * @param deviceType
	 * 	the type that wants to be initialized. If any type works, the argument should be "all"
	 * @param backend
	 * 	the type of backend that wants to be used. It should be either "cuda" or "opencl",
	 * 	if it is anything else it will be set to "opencl"
	 * @return the wanted device where Clesperanto operations can be done.
	 */
	public static DeviceJ getDevice(String deviceName, String deviceType, String backend) {
		BackendJ.setBackend(backend);
		return new DeviceJ(deviceName, deviceType);
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
	 * Return the backend that the device is using.
	 * @return the backend (opencl, cuda) that the device is using
	 * @throws RuntimeException if there is any error finding the backend
	 */
	public String getBackend() {
		String info = this.getInfo();

		int ind = info.indexOf(")");

		if (ind == -1) throw new RuntimeException("Unable to retrieve backend");

		return info.substring(1, ind).toLowerCase();
	}

    /**
     *
     * @return the raw object that is going to be sent to the native Clesperanto library. Without Java wrappers
     */
    public net.clesperanto._internals.jclic.DeviceJ getRaw() {
    	return this.jcppDeviceJ;
    }
}

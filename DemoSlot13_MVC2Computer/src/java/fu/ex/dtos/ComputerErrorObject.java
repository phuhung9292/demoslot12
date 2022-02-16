/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.dtos;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ComputerErrorObject implements Serializable {

    private String idError, cpuError, vgaError, hardDiskError, monitorError, ramError;

    public ComputerErrorObject() {
    }

    public ComputerErrorObject(String idError, String cpuError, String vgaError, String hardDiskError, String monitorError, String ramError) {
        this.idError = idError;
        this.cpuError = cpuError;
        this.vgaError = vgaError;
        this.hardDiskError = hardDiskError;
        this.monitorError = monitorError;
        this.ramError = ramError;
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getCpuError() {
        return cpuError;
    }

    public void setCpuError(String cpuError) {
        this.cpuError = cpuError;
    }

    public String getVgaError() {
        return vgaError;
    }

    public void setVgaError(String vgaError) {
        this.vgaError = vgaError;
    }

    public String getHardDiskError() {
        return hardDiskError;
    }

    public void setHardDiskError(String hardDiskError) {
        this.hardDiskError = hardDiskError;
    }

    public String getMonitorError() {
        return monitorError;
    }

    public void setMonitorError(String monitorError) {
        this.monitorError = monitorError;
    }

    public String getRamError() {
        return ramError;
    }

    public void setRamError(String ramError) {
        this.ramError = ramError;
    }

}

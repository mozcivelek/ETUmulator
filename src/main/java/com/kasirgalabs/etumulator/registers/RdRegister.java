/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.registers;

import com.kasirgalabs.etumulator.Registry;

public class RdRegister implements Register {
    private String value;
    private final int registerNumber;

    public RdRegister(int registerNumber) {
        this.registerNumber = registerNumber;
        this.value = RegisterUtils.getRegisterValue(registerNumber);
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void update() {
        RegisterFile registerFile = Registry.get(RegisterFile.class);
        registerFile.getRegister(registerNumber).setValue(value);
    }
}

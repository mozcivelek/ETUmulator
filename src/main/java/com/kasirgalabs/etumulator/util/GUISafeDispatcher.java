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
package com.kasirgalabs.etumulator.util;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import javafx.application.Platform;

public class GUISafeDispatcher extends BaseDispatcher {
    private static final Logger LOGGER = Logger.getLogger(GUISafeDispatcher.class.getName());

    @Override
    public void notifyObservers(Class<?> clazz, Object arg) {
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            super.notifyObservers(clazz, arg);
            latch.countDown();
        });
        try {
            if(!Platform.isFxApplicationThread()) {
                latch.await();
            }
        } catch(InterruptedException ex) {
            System.exit(-1);
        }
    }

    @Override
    public void notifyObservers(Class<?> clazz) {
        notifyObservers(clazz, null);
    }
}

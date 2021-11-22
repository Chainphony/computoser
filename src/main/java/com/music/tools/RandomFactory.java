/*
 * Computoser is a music-composition algorithm and a website to present the results
 * Copyright (C) 2012-2014 Bozhidar Bozhanov
 * Copyright (C) 2021 Dart Dart
 *
 * Computoser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Computoser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Computoser.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.music.tools;

import java.util.Random;
import java.lang.Class;
import java.lang.Long;
import java.lang.Thread;
import java.math.BigInteger;
import java.util.Optional;

public class RandomFactory {
    // The seed is a 256 bit unsigned integer.
    public static void setSeed(BigInteger s) {
        synchronized (lock) {
            verifySingleThread();
            seed = Optional.of(new BigInteger(s.toByteArray()));
        }
    }

    public static Random createFor(Class c) {
        synchronized (lock) {
            if (seed.isPresent()) {
                verifySingleThread();
                BigInteger shifted = seed.get().shiftRight(currentShift);
                if (currentShift == 256) {
                    currentShift = 0;
                }
                currentShift += 64;
                // Rely on String.hashCode() being stable and not changing between
                // invocations for the same class.
                return new Random(c.getName().hashCode() + shifted.longValue());
            }
        }
        return new Random();
    }

    // Verify that there is a single thread calling RandomFactory methods. We do this to
    // guarantee deterministic generation, given the same seed.
    //
    // Note1: We do that as a shortcut - the right way would be to inspect the code and
    // make sure there are no multiple threads, but that requires thinking...
    //
    // Note2: This method expects that the lock is already taken.
    private static void verifySingleThread() {
        if (singleThreadId.isPresent()) {
            assert singleThreadId.get().longValue() == Thread.currentThread().getId();
        } else {
            singleThreadId = Optional.of(new Long(Thread.currentThread().getId()));
        }
    }

    private static final Object lock = new Object();
    private static Optional<BigInteger> seed = Optional.empty();
    private static Optional<Long> singleThreadId = Optional.empty();
    private static int currentShift = 0;
}

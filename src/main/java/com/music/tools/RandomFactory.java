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
import java.util.Optional;

public class RandomFactory {
    public static void setSeed(long s) {
        seed = Optional.of(new Long(s));
    }

    public static Random createFor(Class c) {
        if (seed.isPresent()) {
            return new Random(c.getName().hashCode() + seed.get().longValue());
        }
        return new Random();
    }

    private static Optional<Long> seed = Optional.empty();
}

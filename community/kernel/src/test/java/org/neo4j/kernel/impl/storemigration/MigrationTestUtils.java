/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
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
package org.neo4j.kernel.impl.storemigration;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.neo4j.kernel.CommonFactories;
import org.neo4j.kernel.IdGeneratorFactory;
import org.neo4j.kernel.impl.nioneo.store.FileSystemAbstraction;
import org.neo4j.kernel.impl.util.FileUtils;

public class MigrationTestUtils
{
    @SuppressWarnings({"unchecked"})
    public static HashMap defaultConfig()
    {
        HashMap config = new HashMap();
        config.put( IdGeneratorFactory.class, CommonFactories.defaultIdGeneratorFactory() );
        config.put( FileSystemAbstraction.class, CommonFactories.defaultFileSystemAbstraction() );
        return config;
    }

    public static int[] makeLongArray()
    {
        int[] longArray = new int[100];
        for (int i = 0; i < 100; i++) {
            longArray[i] = i;
        }
        return longArray;
    }

    static String makeLongString()
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append("characters");
        }
        return builder.toString();
    }

    static void copyRecursively( File fromDirectory, File toDirectory ) throws IOException
    {
        for ( File fromFile : fromDirectory.listFiles() )
        {
            File toFile = new File( toDirectory, fromFile.getName() );
            if ( fromFile.isDirectory() )
            {
                assertTrue( toFile.mkdir() );
                copyRecursively( fromFile, toFile );
            } else
            {
                FileUtils.copyFile( fromFile, toFile );
            }
        }
    }
}

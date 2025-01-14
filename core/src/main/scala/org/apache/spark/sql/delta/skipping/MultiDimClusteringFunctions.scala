/*
 * Copyright (2021) The Delta Lake Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.delta.skipping

// scalastyle:off import.ordering.noEmptyLine
import org.apache.spark.sql.delta.expressions.RangePartitionId

import org.apache.spark.sql.Column
import org.apache.spark.sql.catalyst.expressions.Expression

/** Functions for multi-dimensional clustering of the data */
object MultiDimClusteringFunctions {
  private def withExpr(expr: Expression): Column = new Column(expr)

  /**
   * Conceptually range-partitions the domain of values of the given column into `numPartitions`
   * partitions and computes the partition number that every value of that column corresponds to.
   * One can think of this as an approximate rank() function.
   *
   * Ex. For a column with values (0, 1, 3, 15, 36, 99) and numPartitions = 3 returns
   * partition range ids as (0, 0, 1, 1, 2, 2).
   */
  def range_partition_id(col: Column, numPartitions: Int): Column = withExpr {
    RangePartitionId(col.expr, numPartitions)
  }
}

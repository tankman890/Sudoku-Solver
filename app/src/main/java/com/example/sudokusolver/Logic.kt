package com.example.sudokusolver

fun solve(values: List<Int>) {
    grid = Array(9) { Array(9) { 0 } }
    found = false

    for(i in 0..8) {
        for(j in 0..8) {
            grid[i][j] = values[i * 9 + j]
        }
    }

    for (i in 0..8) {
        for (j in 0..8) {
            if(grid[i][j] == 0) continue
            for (l in 0..8) {
                if (l != j) {
                    if (grid[i][l] == grid[i][j]) {
                        return
                    }
                }
            }
            for (l in 0..8) {
                if (l != i) {
                    if (grid[l][j] == grid[i][j]) {
                        return
                    }
                }
            }
            val x = i / 3 * 3
            val y = j / 3 * 3

            for (l in 0..2) {
                for (m in 0..2) {
                    if (x + l != i && y + m != j) {
                        if (grid[x + l][y + m] == grid[i][j]) {
                            return
                        }
                    }
                }
            }
        }
    }

    fn()
}

var grid: Array<Array<Int>> = Array(9) { Array(9) { 0 } }
var found = false

fun fn() {
    var cnt = 0
    l2@ for (i in 0..8) {
        for (j in 0..8) {
            if (grid[i][j] == 0) {
                cnt++
                l1@ for (k in 1..9) {
                    for (l in 0..8) {
                        if (l != j) {
                            if (grid[i][l] == k) {
                                continue@l1
                            }
                        }
                    }
                    for (l in 0..8) {
                        if (l != i) {
                            if (grid[l][j] == k) continue@l1
                        }
                    }
                    val x = i / 3 * 3
                    val y = j / 3 * 3

                    for (l in 0..2) {
                        for (m in 0..2) {
                            if (x + l != i && y + m != j) {
                                if (grid[x + l][y + m] == k) continue@l1
                            }
                        }
                    }
                    grid[i][j] = k
                    fn()
                    if (found) return
                }
                if (!found) {
                    grid[i][j] = 0
                }
                break@l2
            }
        }
    }

    if (cnt == 0) {
        found = true
    }
}
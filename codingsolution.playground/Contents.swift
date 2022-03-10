import Foundation

// Min max of an array
func minMaxArray(array: [Int]) -> [Int] {
    let n = array.count
    var maxArray = array[0]
    var minArray = array[0]
    
    for i in 1..<n {
        if maxArray <= array[i] {
            maxArray = array[i]
        }
        if minArray >= array[i] {
            minArray = array[i]
        }
        
        }
    
    return [minArray, maxArray]
}

// Swap two variables in place

func swapValues( _ value1: inout Int, _ value2: inout Int) {

    value1 = value1 - value2
    value2 = value1 + value2
    value1 = value2 - value1

}

// Matrix sorted by the sum of its rows

func sumArray(_ array: [Double]) -> Double {
    var sum = 0.0
    for i in 0..<array.count {
        sum += array[i]
    }
    return sum
}

func matrixSortedBySum( _ matrix: [[Double]]) -> [[Double]] {
    
    var sumRowsSorted = Array<Double>(repeating: 0.0, count: matrix.count)
    var matrixSorted = Array<[Double]>(repeating: [0.0], count: matrix.count)
    let inf = Double.infinity
    
    for i in 0..<matrix.count {
        sumRowsSorted[i] = sumArray(matrix[i])
    }
    sumRowsSorted.sort()
    for i in 0..<matrix.count {
         let j = sumRowsSorted.firstIndex(of: sumArray(matrix[i]))!
        matrixSorted[j] = matrix[i]
        sumRowsSorted[j] = inf
    }
    return matrixSorted
}

// Has a cross




func rowSum(matrix: [[Int]], row: Int) -> Int {
    var sum = 0
    for i in 0..<matrix.count {
        if matrix[row][i] == 0 {
            sum = -1
            break
        }
        else {
            sum += matrix[row][i]
        }
        
   }
     return sum
}

func columnSum(matrix: [[Int]], column: Int) -> Int {
    var sum = 0
    for i in 0..<matrix.count {
        if matrix[i][column] == 0 {
            sum = -1
            break
        }
        else {
            sum += matrix[i][column]
        }
        
   }
     return sum
}

    
func hasCross(_ matrix:[[Int]]) -> Int {
    var count1 = 0
    var count2 = 0
    
    for i in 0..<matrix.count {
        if rowSum(matrix: matrix, row: i) == -1 {
            continue
        }
        else if rowSum(matrix: matrix, row: i) == matrix.count {
            count1 += 1
        }
        else if rowSum(matrix: matrix, row: i) == 2*matrix.count {
            count2 += 1
        }
    }
    if (count1 > 0 && count2 > 0) || (count1 == 0 && count2 == 0){
        return 0
    }
    if count1 == 0 && count2 > 0 {
        for i in 0..<matrix.count {
            if columnSum(matrix: matrix, column: i) == 2*matrix.count {
                return 2
            }
            }
        }
    if count1 > 0 && count2 == 0 {
        for i in 0..<matrix.count {
            if columnSum(matrix: matrix, column: i) == matrix.count {
                return 1
            }
        }
    }
    return 0
}
    
  // Pairs in array with a given sum



func pairsSum(array: [Int],sum: Int )-> [[Int]] {
   var pairs = [[Int]]()
    
    
    for i in 0..<array.count {
        if pairs.contains([sum - array[i],array[i]]) {
            continue
        }
        
        if array.contains(sum - array[i])  {
            pairs += [[array[i],sum - array[i]]]
        }
        }
    return pairs
    
}

// Binary search



func binarysearch(array: [Int],guess: Int) -> Bool {
    var high = array.count
    var low = 0
    
    var mid: Int

    while low < high {
        mid = (high + low) / 2
        if array[mid] == guess {
            return true
            }
        else {
        if array[mid] < guess {
           low = mid + 1
        }
        else {
            high = mid
        }
     }
  }

    return false
}

// Peak of an array



func peakArray(_ array: [Int] ) -> Int {
      
    let mid = array.count / 2
    
    if array.count == 1 {
        return array[0]
    }
    if array.count == 2 {
        return max(array[0],array[1])
    }
    
    if array[mid] >= array[mid+1] && array[mid] >= array[mid-1] {
        return array[mid]
    }
    
    if array[mid] < array[mid+1]  {
        return peakArray(Array(array[mid+1...array.count-1]))
        }
    
    if array[mid] < array[mid-1]  {
        return peakArray(Array(array[0...mid-1]))
        }
    return 0
}

// Peak matrix



func submatrix0fmatrix(matrix: [[Int]], firstColumn: Int, secondColumn: Int) -> [[Int]] {

    var submatrix = Array(repeating: [0], count: matrix.count)

    for i in 0..<matrix.count {
        submatrix[i] = Array(matrix[i][firstColumn...secondColumn])
    }
    return submatrix
    }

func column(matrix:[[Int]], index: Int) -> [Int] {

    var column = Array(repeating: 0, count: matrix.count)

    for i in 0..<matrix.count {
        column[i] = matrix[i][index]
    }

    return column
}







func peakMatrix(_ matrix: [[Int]] ) -> Int {

    let n = matrix[0].count / 2
    let p = matrix[0].count

     if p == 1 {
        return column(matrix: matrix, index: 0).max()!
    }
    if p == 2 {
        return max(column(matrix: matrix, index: 0).max()!,column(matrix: matrix, index: 1).max()!)
    }

    let m = column(matrix: matrix, index: n).max()!
    let j = column(matrix: matrix, index: n).firstIndex(of: m)!

    if matrix[j][n-1] <= m && matrix[j][n+1] <= m {
        return m
    }
    if matrix[j][n-1] > m {
        return peakMatrix(submatrix0fmatrix(matrix: matrix, firstColumn: 0, secondColumn: n-1))
    }

    if matrix[j][n+1] > m {
        return peakMatrix(submatrix0fmatrix(matrix: matrix, firstColumn: n+1, secondColumn: p-1))
    }
    return 0
}



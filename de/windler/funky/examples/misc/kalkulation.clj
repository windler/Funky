(ns de.windler.jfunk.examples.kalkulation)

(def func1 (fn [x y] (* x y)))

(defn fak [n]
  (if (= n 0) 1
   (* n (fak (- n 1)))))

(defn pi2 [] (/ 3.14 1))
(defn pi [] (/ 3.14 1))

(defn async [i] 
  (if (= i 0) 1
    (+ 1 (async (- i 1)))))
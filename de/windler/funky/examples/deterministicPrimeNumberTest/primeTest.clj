(ns de.windler
  (:use clojure.test))


(defn checkDivideable [n m]
  "Checks whether n mod m = 0"
    (= (mod n m) 0))

(defn primeBruteforce [n val]
  "tests prime bruteforce. This implementation is really, really dump. 
   For example purpose only!"
  (loop [m 2
         result val]
    (let [v (checkDivideable n m)]
      (if (>= m n) (not result)
        (recur (+ m 1) (or v result))))
    ))

(with-test
	(defn prime [n] 
	  "Tests whether n is a prime number. This will happen bruteforce 
	   beacause we need some time to elapse for this example ;)"
   (if (> n 1) (primeBruteforce n false) false))
 
  (is (prime 13))
  (is (prime 19))
  (is (prime 5))
  (is (= false (prime 0)))
  (is (= false (prime 1)))
  (is (= false (prime 4)))
  (is (= false (prime 24)))
 )

(run-all-tests)

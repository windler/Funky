(ns de.windler.transaction)

(def account (ref nil))

(defn init! [money]
  "initializes the account with the given money and returns the money"
  (dosync (ref-set account money)
    money))

(defn ammount []
  "gets the current ammount stored in account"
  @account)

(defn transfer! [money]
  "transfers the money if possible."
  (dosync 
    (if (>= (+ @account money) 0)
      (do
        (Thread/sleep 1000)
        (alter account + money)
        true)
      false
      )))
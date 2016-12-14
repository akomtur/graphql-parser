(ns graphql.core
    (:require [clojure.spec :as s]
              [graphql.spec :as spec]))

(def type-registry (atom {}))

(defn register-type
  [type]
  (if (s/valid? ::spec/type type)
    (swap! type-registry (fn [m v] (assoc m (:type/name v) v)) type)))

(defn get-type [type-name]
  (if (keyword? type-name)
    (type-name @type-registry)
    ((keyword type-name) @type-registry)))

(defn type
  [name & attributes]
  #:type{:name name
         :attributes attributes})

(defn attr
  [name type]
  (if (map? type)
    (merge #:attr{:name name :type type}
           type)
    #:attr{:name name :type type}))

(defn attr-type
  ([type]
   (attr-type type false))
  ([type required]
   (if (map? type)
     (merge type
      #:attr{:required required})
     #:attr{:type type
            :required required})))

(defn array-of
  [type]
  #:attr{:type type :isArray true})

(defn argument
  ([name type]
   #:argument{:name name :type type}))


(def sample {:object/type :type
             :object/data {:type/name :Human
                           :type/interface :Character
                           :type/attributes [:attribute/name "name"
                                             :attribute/type "String"
                                             :attribute/required true]}})

(ns graphql.spec
    (:require [clojure.spec :as s]))

(s/def ::object
       (s/keys :req [:object/type
                     :object/data]))
(s/def :object/type keyword?)
(s/def :object/data #{::type})

(s/def ::type
       (s/keys :req [:type/name
                     :type/interface
                     :type/attributes]))
(s/def :type/name keyword?)
(s/def :type/interface keyword?)
(s/def :type/attributes (s/coll-of ::attribute))

(s/def ::attribute
       (s/keys :req [:attr/name
                     :attr/type]
               :opt [:attr/isArray
                     :attr/required
                     :attr/arguments]))

(s/def :attr/name string?)
(s/def :attr/type string?)
(s/def :attr/isArray boolean?)
(s/def :attr/required boolean?)

(s/def :attr/arguments (s/coll-of :argument))

;;argument definition
(s/def ::argument
       (s/keys :req [:argument/name
                     :argument/type]
               :opt [:argument/required
                     :argument/default]))

(s/def :argument/name string?)
(s/def :argument/type keyword?)
(s/def :argument/required boolean?)
(s/def :argument/default string?)

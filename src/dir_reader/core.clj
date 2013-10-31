(ns dir-reader.core
  (:require
    [net.cgrand.enlive-html :as html]
    [clj-http.client :as client]
    )
  )

;; default root url (make sure it contains trailing slash)
(def base-url "http://localhost:8000/")

(defn fetch-url
  "Returns body of response parsed as html/html-resource"
  [url]
  (-> (client/get url) :body java.io.StringReader. html/html-resource))


(defn extract-names
  "Extracts file/dir name from ul>li>a"
  [body]
  (flatten (map :content (html/select body [:ul :li :a]))))

(defn traverse-dir
  "Recursively traverses directory"
  [root]

  ;; reduce takes a function named reducer tha accepts
  ;; two arguments: 
  ;; - accumulator (files in this case)
  ;; - current element from sequence
  (reduce (fn [files name]
            (concat files 
                    (if (.endsWith name "/")
                      (traverse-dir (str root name))
                      [(str root name)])))
          []
          (extract-names (fetch-url root))))

(defn -main [root]
  (println "Fetching data from: " root)
  (println (traverse-dir (or root base-url))))




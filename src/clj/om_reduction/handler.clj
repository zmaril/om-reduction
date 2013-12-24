(ns om-reduction.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [ring.util.response :as resp]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (resp/redirect "/index.html"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

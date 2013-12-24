(ns om-reduction.app
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def state (atom {:calling? false}))

(defn call-button []
  (println (:calling? @state))
  (if (:calling? @state)
    (dom/a #js {:onClick (fn [_]
                           (println "on")
                           (swap! state update-in [:calling?]
                                  (fn [_] false)))}
           (str "On"))
    (dom/button #js {:onClick (fn [_]
                           (println "off")
                           (swap! state update-in [:calling?]
                                  (fn [_] true)))}
           (str "Off"))))

(defn redux-app [app]
  (reify 
    om/IRender 
    (render [_ owner]
      (dom/div nil 
               (call-button)))))

(om/root app-state redux-app (.getElementById js/document "container"))

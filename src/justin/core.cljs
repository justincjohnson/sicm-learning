(ns justin.core
  (:refer-clojure :exclude [+ - * / zero? ref partial])
  (:require [sicmutils.env :as e :include-macros true]
            [justin.p5 :as p5]))

(e/bootstrap-repl!)

(+ 'x 'y)

((exp D) (literal-function 'f))

(((exp D) (literal-function 'f)) 'x)

(take 4 (((exp D) (literal-function 'f)) 'x))


(p5/defsketch example
  (setup [p]
         (.createCanvas p 640 480))
  (draw [p]
        (.fill p (if (.-mouseIsPressed p) 0 255))
        (.ellipse p (.-mouseX p) (.-mouseY p) 80 80)))


(macroexpand-1 '(p5/defsketch example
                  (setup [p]
                         (.createCanvas p 640 480))
                  (draw [p]
                        (.fill p (if (.-mouseIsPressed p) 0 255))
                        (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))))

  ;; yields

(do (justin.p5/ensure-parent "example")
    (def example (justin.p5/instance
                  [["setup" (clojure.core/fn [p]
                              (.createCanvas p 640 480))]
                   ["draw" (clojure.core/fn [p]
                             (.fill p (if (.-mouseIsPressed p) 0 255))
                             (.ellipse p (.-mouseX p) (.-mouseY p) 80 80))]]
                  "example")))
/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;


import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
        router.GET().route("/").with(ApplicationController.class, "index");
//        router.GET().route("/hello_world.json").with(ApplicationController.class, "helloWorldJson");
        router.GET().route("/AcesUp").with(ApplicationController.class, "acesUp");



        router.POST().route("/moveCard/{columnFrom}/{columnTo}").with(ApplicationController.class, "moveCard");
        router.POST().route("/removeCard/{column}").with(ApplicationController.class, "removeCard");

        router.GET().route("/game").with(ApplicationController.class, "gameGet");
        router.POST().route("/hit/{theCol}").with(ApplicationController.class, "hitPost");
        router.POST().route("/bet/{bet}").with(ApplicationController.class, "userBet");
        router.POST().route("/stay/{theCol}").with(ApplicationController.class, "stayPost");
        router.POST().route("/ddown/{theCol}").with(ApplicationController.class, "ddownPost");
        router.POST().route("/split").with(ApplicationController.class, "splitPost");


        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/.*").with(ApplicationController.class, "index");
    }

}

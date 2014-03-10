#Play 2.2.1 and Liquibase 3.1.1 plugin

Forked from https://github.com/mknittig/play-liquibase

Slightly adapted plugin from the fork base, changes are :
 * use a correct package naming to be able to deploy on maven central
 * updated dependencies to match Play Framework! and Liquibase latests
 * fix typos
 * updated documentation
 
 
I prefer Liquibase DB evolutions to play 2.0 Evolutions mechanism
This is simple play 2.1 plugin for liquibase usage with Scala 2.10.x.

Main script files for your DB must be at `conf/liquibase/--dbName--/modules.xml`

Use contexts in your scripts to manage test and prod data and schema. Available contexts are:
 1. "test" - for test mode
 2. "dev" - for dev mode
 3. "prod" - for production mode

In dev and production mode use applyLiquibase.<dbName>=true properties to apply scripts.

# Usage
To use it in your project, add the following dependency in your Build file

    `com.codetroopers.play %% play-liquibase % 1.0`
    
Then enable the plugin in your `play.plugins` file, add the following line (adjust plugin order to your convenience) :
    `10000:com.codetroopers.play.LiquibasePlugin`
    
You will surely need to add to your application.conf the property `applyLiquibase.default=true` if you want to avoid troubles.

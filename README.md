# Android Studio - Comment créer une application avec un menu

<br>

> Tutoriel généré par EdgeCoco puis testé, modifié et rédigé par Panda.
> 
> Retrouvez tout le code présent dans ce tutoriel dans le repository.

<br>

## Configuration

> Android Studio Girafe > Oreo (API 27)
> 
> Version 2022.3.1

On commence par créer une application. Pour ma part j'ai choisi de créer une ``Empty view activity``.

> Tenter d'exectuer l'application, si jamais ça ne fonctionne pas il est probable que la version du SDK ne soit pas là bonne. En tout cas c'etait mon cas.

> Pour régler ce problème 
> - Clique droit sur ``app`` dans la vue Android
> - ``Open Module Settings``
> - Puis on change ``Compile SDK version`` pour la version 34.

<br>

Notons que ceci est la listes des dépendences que j'utilise :

**Nom du fichier : ``build.gradle.kts``**
```KTS
// ...
dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```

<br>

## Les valeurs de ``values/strings.xml``

```XML
<resources>
    <!-- MAIN APP -->
    <string name="app_name">Nom de l\'application</string>

    <!-- NAVIGATION DRAWER -->
    <string name="navigation_drawer_open">Open navigation drawer</string>
    <string name="navigation_drawer_close">Close navigation drawer</string>
    <string name="all_acts">Toutes les activités</string>

    <!-- ACTIVITE 1 -->
    <string name="act1_name">Activité 1</string>

    <!-- ACTIVITE 2 -->
    <string name="act2_name">Activité 2</string>

</resources>
```

<br>

## Création du menu

On va commencer par créer le contenu du menu dans un nouveau fichier xml que nous allons mettre dans le dossier ``res/menu``.

**Nom du fichier : ``drawer_menu.xml``**

```XML
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <group android:checkableBehavior="single">
        <item
            android:id="@+id/nav_home"
            android:title="Accueil" />
        <item
            android:id="@+id/nav_random_activity"
            android:title="@string/all_acts">
            <menu>
                <item
                    android:id="@+id/nav_random_number"
                    android:title="@string/act1_name" />
                <item
                    android:id="@+id/nav_choose_between"
                    android:title="@string/act2_name" />
            </menu>
        </item>
    </group>
</menu>
```

<br>

On peut également créer un header pour notre menu dans un nouveau fichier xml que nous allons mettre dans ``res/layout``.

**Nom du fichier : ``drawer_header``**

```XML
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="@dimen/nav_header_height"
    android:background="#B2B9E1"
    android:gravity="center"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/header_icon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:contentDescription="Logo de l'application qui sert de lien vers la page d'acceuil"
        android:src="@drawable/ic_launcher_foreground" />

</LinearLayout>
```

Notez qu'il vous faudra un fichier supplémentaire dans ``res/values``

**Nom du fichier : ``dimens.xml``**

``` xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="nav_header_height">176dp</dimen>
</resources>
```

<br>

Il suffit désormais d'ajouter le menu dans ``activity_main.xml``.
```XML
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout>
        <!-- On ajoutera le contenu de la page ici (toolbar + fragment container) -->
    </LinearLayout>

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:menu="@menu/drawer_main"
        app:headerLayout="@layout/drawer_header"/>
</androidx.drawerlayout.widget.DrawerLayout>

```

<br>

On peut désormais ajouter le java pour le menu

**Nom du Fichier : ``ActivityMain.java``**

```java
package fr.ellecknia.exemple;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // --- NAVIGATION ---
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // --- DEFAULT FRAGMENT CHOICE ---
        if (savedInstanceState == null) {
            // TODO : Ajouter le comportement
        }

        // --- HEADER ---
        ImageView headerIcon = navigationView.getHeaderView(0).findViewById(R.id.header_icon);
        headerIcon.setOnClickListener(v -> {
            // TODO : Ajouter le comportement

            navigationView.setCheckedItem(R.id.nav_home);
        });

        // --- DRAWER ---
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            // Checker le bon item visuellement
            uncheckAllMenuItems(navigationView.getMenu());
            menuItem.setChecked(true);

            int id = menuItem.getItemId();

            if (id == R.id.nav_home) {
                // TODO : Ajouter le comportement

            } else if (id == R.id.nav_act1) {
                // TODO : Ajouter le comportement

            } else if (id == R.id.nav_act2) {
                // TODO : Ajouter le comportement

            } 


            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        // --- Gestion de l'ouverture et fermeture du menu ---
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    // Fonction qui permet de checker le bon item visuellement
    public static void uncheckAllMenuItems(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                uncheckAllMenuItems(item.getSubMenu());
            } else {
                item.setChecked(false);
            }
        }
    }

    // Cette fonction est deprecated dans ma version mais je n'ai pas trouvé d'autres moyens pour l'instant
    // Elle permet, lorsque l'on clique sur retour alors que le menu est ouvert ça ferme le menu
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
```

## Gestion des fragments

### Création

Afin de pourvoir changer de page sans changer d'activités il faut créer des fragments. Ce pourquoi nous allons créer 3 fragments :

- ``Home.java`` qui sera lié à ``fragment_home.xml``

- ``Activity1.java`` qui sera lié à ``fragment_act1.xml``

- ``Activity2.java`` qui sera lié à ``fragment_act2.xml``

Pour cela, on peut ajouter un fragment comme on ajouterais une activité : ``File > New > Fragment > Fragment (Blank)``. 


## Modification des données par défaut

J'ai ensuite enlever la plupart du contenu qui est ajouté automatiquement dans le fragment pour ne laisser que la fonction ``onCreateView`` dans les fichiers java. 

J'ai également modifié le type de vue pour chaque fragment que j'ai crée (``Frame Layout`` --> ``Constraint Layout``).

> **Comment changer le type de vue ?** <br> 
> Quand vous ouvrez un layout, vous avez accès a différentes fenêtres dont ``Component Tree``, c'est ici que vous pouvez facilement accéder aux différents composants de votre layout. <br> 
> D'ici, vous pouvez faire un clique droit sur un composant. Choisissez ``Convert view`` pour changer le type de composant.

A titre d'exemple, voici ce que j'ai fais pour le fragment qui me sers de page d'acceuil.


**Nom du fichier : ``Home.java``**

```java
package fr.ellecknia.exemple;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Home extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}

```

<br>

**Nom du fichier : ``fragment_home.xml``**

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Home">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        android:text="@string/app_name"
        android:textAppearance="@style/TextAppearance.AppCompat.Display1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

<br>

### Modification du fichier ``MainActivity.java``

Maintenant que nos différents fragments sont créés, nous pouvons ajouter les liens vers les fragments dans le menu. Pour cela nous allons compléter les ``TODO``que nous avions laisser précédement dans le fichier java.

**On ajoute le fragment ouvert par défaut lors de l'ouverture de l'activité.** Ici, il choisis de la page d'acceuil de mon application.
``` java
        // --- DEFAULT FRAGMENT CHOICE ---
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
```
**On ajoute le comportement du header.** Ici, lorsqu'on clique sur l'icone, on retourne sur la page d'acceuil.
```java
        // --- HEADER ---
        ImageView headerIcon = navigationView.getHeaderView(0).findViewById(R.id.header_icon);
        headerIcon.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Home())
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

            navigationView.setCheckedItem(R.id.nav_home);
        });
```
**On ajoute le comportement pour chaque item lorsqu'il est séléctionné dans le menu.** Ici, chaque item renvoie vers un fragment de l'application.
```java
        // --- DRAWER ---
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            // Checker le bon item visuellement
            uncheckAllMenuItems(navigationView.getMenu());
            menuItem.setChecked(true);

            int id = menuItem.getItemId();

            if (id == R.id.nav_home) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                new Home())
                        .commit();

            } else if (id == R.id.nav_act1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                new  Activity1())
                        .commit();

            } else if (id == R.id.nav_act2) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                new Activity2())
                        .commit();
            }


            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

```

<br>

Il n'est pas improbable que vous ayez quelques import a ajouter mais normalement, à partir de là, vous devriez avoir une application basique avec :

**Un toolbar contenant**

- Le nom de votre application 

- Un menu burger qui lorsq

**Un menu composé d'un header et d'une liste d'item**

- Le header a une image qui renvoie vers le fragment correspondant à la page d'acceuil lorsqu'on clique dessus.

- Un menu de liens d'ouvertures de fragment : Acceuil / Activité 1 et Activité 2

**Des fragments relativement vides**

- Pour l'instant nous avons seulement mis le titre de chaque fragment dans ces derniers.

<br>

## Personnalisation des fragments

Tout d'abords, vous pouvez modifier les fichiers xml correspondant au layout des fragments.

Ensuite vous pouvez modifier les fichiers java correspondant aux fragments que vous souhaitez modifier.

Ici, je vais ajouter des boutons dans le fragment correspondant à la page d'acceuil pour renvoyer vers les autres fragments.

**Nom du fichier : ``fragment_homme.xml``**

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Home">
    
    <TextView
        android:id="@+id/title_home"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        android:gravity="center"
        android:text="@string/app_name"
        android:textAppearance="@style/TextAppearance.AppCompat.Display1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btn_act1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/act1_name"
        app:layout_constraintBottom_toTopOf="@+id/btn_act2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/title_home"
        app:layout_constraintVertical_bias="0.0" />

    <Button
        android:id="@+id/btn_act2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="120dp"
        android:text="@string/act2_name"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/btn_act1" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

<br>

**Nom du fichier : ``Home.java``**

```java
package fr.ellecknia.exemple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;


public class Home extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        // --- To set the right selected item ---
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        MainActivity.uncheckAllMenuItems(navigationView.getMenu());

        // --- GO ACTIVITY 1 ---
        Button buttonChooseBetween = view.findViewById(R.id.btn_act1);
        buttonChooseBetween.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Activity1())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_act1);
        });

        // --- GO TO ACTIVITY 2 ---
        Button buttonRandCo = view.findViewById(R.id.btn_act2);
        buttonRandCo.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new Activity2())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_act2);
        });

        return view;
    }
}
```

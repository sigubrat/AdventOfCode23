{  
  description = "aoc23";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          overlays = [
            (f: p: {
              sbt = p.sbt.override { jre = p.jdk21_headless; };
            })
          ];
        };
        jdk = pkgs.jdk21_headless;

        jvmInputs = with pkgs; [
          jdk
          coursier
          sbt
          scalafmt
          scala-cli
        ];

        
        jvmHook = ''
          JAVA_HOME="${jdk}"
        '';


      in
      {
        devShells.default = pkgs.mkShell {
          name = "aoc23";
          buildInputs = jvmInputs;
          shellHook = jvmHook;
        };
      }
    );

}

@echo off
set CLASS=Mahmud_Thread
del results.txt 2>nul

for %%T in ("1 1000" "2 10000" "5 10000" "10 50000" "20 50000" "50 50000" "100 50000") do (
  echo ===== TEST: %%~T ===== >> results.txt
  echo --- Thread-safe --- >> results.txt
  java %CLASS% %%~T true >> results.txt
  for %%R in (1 2 3 4 5) do (
    echo --- Unsafe run %%R --- >> results.txt
    java %CLASS% %%~T false >> results.txt
  )
)
echo Done! Open results.txt
pause
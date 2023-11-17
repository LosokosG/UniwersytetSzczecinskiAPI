API dla uniwersyteu szczecińskiego, stworzone aby zbierać informacje z planu zajęć konkretnej grupy / kierunku oraz wykładowców.
Celem jest podłączenie do ChatGPT, tworząc asystenta przy użyciu GPTs mającego dostęp do tego API.

Aktualnie jest hostowany pod: http://44.205.179.201:8081/api

Możliwe endpointy:
/fields - Wszystkie możliwe kierunki
/groupschedule/{groupNumer} - Plan konkretnej grupy
/fieldschedule/{field}/{year}/{degree} - Plan konkretnego kierunku, konkretnego roku i stopnia

( niektóre w aktualnej wersji mogą zwracać błąd, ponadto projekt na github nie jest rzeczywistym gotowym projektem )
